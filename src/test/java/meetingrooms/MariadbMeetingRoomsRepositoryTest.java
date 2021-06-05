package meetingrooms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MariadbMeetingRoomsRepositoryTest {

    MariadbMeetingRoomsRepository mariadbMeetingRoomsRepository = new MariadbMeetingRoomsRepository();

    @BeforeEach
    public void init() {
        mariadbMeetingRoomsRepository.save("Nagytárgyaló", 6, 10);
        mariadbMeetingRoomsRepository.save("Tárgyaló", 5, 8);
        mariadbMeetingRoomsRepository.save("Kistárgyaló", 3, 4);
        mariadbMeetingRoomsRepository.save("Konferencia", 10, 10);
    }

    @AfterEach
    public void clear() {
        mariadbMeetingRoomsRepository.deleteAll();
    }


    @Test
    void testGetOrderedNames() {
        List<String> orderedNames = Arrays.asList(
                "Kistárgyaló",
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló");

        assertEquals(orderedNames, mariadbMeetingRoomsRepository.getOrderedNames());
    }

    @Test
    void testGetReversedNames() {
        List<String> reversedNames = Arrays.asList(
                "Tárgyaló",
                "Nagytárgyaló",
                "Konferencia",
                "Kistárgyaló");

        assertEquals(reversedNames, mariadbMeetingRoomsRepository.getReversedNames());
    }


    @Test
    void testGetEvenOrderedNames() {
        List<String> evenOrderedNames = Arrays.asList(
                "Konferencia",
                "Tárgyaló");

        assertEquals(evenOrderedNames, mariadbMeetingRoomsRepository.getEvenOrderedNames());
    }

    @Test
    void getMeetingRoomsOrderedByAreaDesc() {
        List<String> namesByOrderedAreaDesc = Arrays.asList(
                "Konferencia",
                "Nagytárgyaló",
                "Tárgyaló",
                "Kistárgyaló");

      assertEquals(namesByOrderedAreaDesc,  mariadbMeetingRoomsRepository.getMeetingRoomsOrderedByAreaDesc().stream().map(MeetingRoom::getName).collect(Collectors.toList()));
    }
}