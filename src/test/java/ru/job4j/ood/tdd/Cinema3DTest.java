package ru.job4j.ood.tdd;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

import java.util.Calendar;
import java.util.List;

@Disabled
public class Cinema3DTest {
    @Test
    public void whenBuyThenGetTicket() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket).isEqualTo(new Ticket3D());
    }

    @Test
    public void whenAddSessionThenItExistsBetweenAllSessions() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(ses -> true);
        assertThat(sessions).contains(session);
    }

    @Test
    public void whenBuyOnInvalidRowThenGetException() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        assertThatThrownBy(() -> cinema.buy(account, -1, 1, date)).
                isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void whenNoMoreEmptyPlacesThenNoTicketAndNull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        Ticket ticket1 = cinema.buy(account, 1, 1, date);
        Ticket ticket2 = cinema.buy(account, 1, 2, date);
        Ticket ticket3 = cinema.buy(account, 1, 3, date);
        assertThat(ticket3).isNull();
    }

    @Test
    public void whenAddSessionWithSameTimeThenGetException() {
        Cinema cinema = new Cinema3D();
        Session session1 = new Session3D();
        Session session2 = new Session3D();
        Calendar time = Calendar.getInstance();
        time.set(Calendar.HOUR_OF_DAY, 10);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        session1.setTime(time);
        session2.setTime(time);
        cinema.add(session1);
        assertThatThrownBy(() -> cinema.add(session2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Session with the same time already exists");
    }
}