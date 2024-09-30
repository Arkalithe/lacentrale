package fr.humanbooster.lacentral.service.interfaces;

public interface ServiceInterfaceBase<T, L, C>{

    T create(C object);

    Boolean delete(L Long);


}