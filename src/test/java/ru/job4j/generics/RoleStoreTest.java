package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenRoleNameIsMargarita() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Margarita"));
        Role result = store.findById("3");
        assertThat(result.getRoleName(), is("Margarita"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("3", "Voland"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleNameIsMargarita() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Margarita"));
        store.add(new Role("1", "Voland"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Margarita"));
    }

    @Test
    public void whenReplaceThenRoleNameIsMaster() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Margarita"));
        store.replace("1", new Role("1", "Master"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Master"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRoleName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Margarita"));
        store.replace("10", new Role("10", "Master"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Margarita"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Margarita"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleNameIsMargarita() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Margarita"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Margarita"));
    }
}
