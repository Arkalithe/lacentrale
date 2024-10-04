package fr.humanbooster.lacentral.configuration;

import fr.humanbooster.lacentral.entity.*;
import fr.humanbooster.lacentral.entity.embededid.UserListingId;
import fr.humanbooster.lacentral.repository.*;
import lombok.AllArgsConstructor;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final FuelRepository fuelRepository;
    private final ListingRepository listingRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final Faker faker = new Faker(Locale.FRANCE);
    private final BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args){
        Random random = new Random();
        if (userRepository.count() > 0) {
            System.out.println("Donne deja existente, annullation du lancemment de la generation des fake donnée");
            return;
        }

        List<User> users = getUsers();

        List<Brand> brands = getBrandList();

        List<Model> models = getModels(brands, random);

        List<Fuel> fuels = getFuels();

        List<Address> addresses = addressRepository.findAll();

        List<Listing> listings = getListings(models, random, fuels, addresses, users);


        extracted(users, random, listings);

        System.out.println("Fake data generation fini.");
    }

    private void extracted(List<User> users, Random random, List<Listing> listings) {
        Set<String> favoriteKeys = new HashSet<>();
        for (int i = 0; i < 2000; i++) {
            User user = users.get(random.nextInt(users.size()));
            Listing listing = listings.get(random.nextInt(listings.size()));
            String key = user.getUuid() + "-" + listing.getUuid();

            if (!favoriteKeys.contains(key)) {
                Favorite favorite = new Favorite();
                UserListingId id = new UserListingId();
                id.setUser_uuid(user.getUuid());
                id.setListing_uuid(listing.getUuid());
                favorite.setId(id);
                favorite.setCreatedAt(LocalDateTime.now());
                favorite.setUser(user);
                favorite.setListing(listing);
                favoriteRepository.save(favorite);
                favoriteKeys.add(key);
            }
        }
    }

    private List<Listing> getListings(List<Model> models, Random random, List<Fuel> fuels, List<Address> addresses, List<User> users) {
        List<Listing> listings = new ArrayList<>();

        for (int i = 0; i < 500; i++) {
            Listing listing = new Listing();
            listing.setPrice((long) faker.number().numberBetween(5000, 50000));
            listing.setMileage((long) faker.number().numberBetween(10000, 200000));
            listing.setProducedAt(LocalDateTime.ofInstant(
                    (faker.date().past(365 * 20, TimeUnit.DAYS)).toInstant(),
                    ZoneId.systemDefault()));
            listing.setCreatedAt(LocalDateTime.now());
            listing.setDescription(faker.lorem().paragraph());
            listing.setTitle(faker.commerce().productName());
            listing.setModel(models.get(random.nextInt(models.size())));
            listing.setFuel(fuels.get(random.nextInt(fuels.size())));
            listing.setAddress(addresses.get(random.nextInt(addresses.size())));
            listing.setOwner(users.get(random.nextInt(users.size())));
            listing = listingRepository.save(listing);
            listings.add(listing);


            int imageCount = random.nextInt(5) + 1;
            for (int j = 0; j < imageCount; j++) {
                Image image = new Image();
                image.setPath("images/" + faker.file().fileName(null, null, null, null));
                image.setListing(listing);
                imageRepository.save(image);
            }
        }
        return listings;
    }

    private List<Fuel> getFuels() {
        List<Fuel> fuels = new ArrayList<>();
        String[] fuelTypes = {"Gasoline", "Diesel", "Electric", "Hybrid", "Hydrogen"};
        for (String fuelType : fuelTypes) {
            Fuel fuel = new Fuel();
            fuel.setType(fuelType);
            fuel.setLogo(fuelType.toLowerCase() + "_logo.png");
            fuel = fuelRepository.save(fuel);
            fuels.add(fuel);
        }
        return fuels;
    }

    private List<Model> getModels(List<Brand> brands, Random random) {
        List<Model> models = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Model model = new Model();
            model.setName(faker.bothify("Model ???"));
            Brand brand = brands.get(random.nextInt(brands.size()));
            model.setBrand(brand);
            model = modelRepository.save(model);
            models.add(model);
        }
        return models;
    }

    private List<Brand> getBrandList() {
        List<Brand> brands = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Brand brand = new Brand();
            brand.setName(faker.company().name());
            brand = brandRepository.save(brand);
            brands.add(brand);
        }
        return brands;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setEmail(faker.internet().emailAddress());
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setPassword(passwordEncoder.encode("12345"));
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setBirthAt(generateRandomDate(
                    Instant.now().minusSeconds(999999999)
                            .minusSeconds(999999999)
                            .minusSeconds(999999999)).toLocalDate());
            user.setCreatedAt(LocalDateTime.now());
            String roles = "[\"ROLE_USER\"";
            if (i == 1L) {
                roles += ", \"ROLE_ADMIN\"";
            }
            roles += "]";
            user.setRoles(roles);
            user = userRepository.save(user);
            users.add(user);

            Address address = new Address();
            address.setStreetNumber(faker.address().buildingNumber());
            address.setStreetName(faker.address().streetName());
            address.setZipCode(faker.address().zipCode());
            address.setCity(faker.address().city());
            address.setLongitude(Float.parseFloat(faker.address().longitude().replace(",", ".")));
            address.setLatitude(Float.parseFloat(faker.address().latitude().replace(",", ".")));
            address.setUser_uuid(user);
            addressRepository.save(address);
        }
        return users;
    }

    private LocalDateTime generateRandomDate(Instant start) {
        Faker faker = new Faker();
        Instant randomDate = faker.timeAndDate().between(
                start,
                Instant.now());
        return randomDate.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}