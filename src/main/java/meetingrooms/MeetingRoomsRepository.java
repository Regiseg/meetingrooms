package meetingrooms;

public interface MeetingRoomsRepository {

    void save(String name, int width, int length);

    void printNames();

    void printNamesReverse();

    void printEvenNames();

    void printArea();

    void printMeetingRoomsWithName(String name);

    void printMeetingRoomsContains(String part);

    void printAreasLargerThan(int area);
}
