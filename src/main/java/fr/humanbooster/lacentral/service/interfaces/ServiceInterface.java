package fr.humanbooster.lacentral.service.interfaces;

public interface ServiceInterface<T, L, C, U> extends ServiceInterfaceBase<T,L,C>{

    T update(U object, L id);

    T findById(L id);
}