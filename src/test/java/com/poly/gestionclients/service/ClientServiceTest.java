package com.poly.gestionclients.service;

import com.poly.gestionclients.entities.Client;
import com.poly.gestionclients.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    private Client mockClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockClient = new Client();
        mockClient.setId(1); // Utilise les setters générés par Lombok
        mockClient.setName("John Doe");
        mockClient.setBirthDay(LocalDate.of(1990, 1, 1));
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(mockClient));
        List<Client> clients = clientService.getAllClients();
        assertEquals(1, clients.size());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(mockClient));
        Optional<Client> client = clientService.getClientById(1);
        assertTrue(client.isPresent());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testSaveClient() {
        when(clientRepository.save(mockClient)).thenReturn(mockClient);
        Client savedClient = clientService.saveClient(mockClient);
        assertEquals("John Doe", savedClient.getName());
        verify(clientRepository, times(1)).save(mockClient);
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById(1);
        clientService.deleteClient(1);
        verify(clientRepository, times(1)).deleteById(1);
    }

}