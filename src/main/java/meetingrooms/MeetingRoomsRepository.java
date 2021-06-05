package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

     List<String> getOrderedNames();

     List<String> getReversedNames();

    List<String> getEvenOrderedNames();

    List<MeetingRoom> getMeetingRoomsOrderedByAreaDesc();

    void printMeetingRoomsWithName(String name);

    void printMeetingRoomsContains(String part);

    void printAreasLargerThan(int area);

    void deleteAll();
}
