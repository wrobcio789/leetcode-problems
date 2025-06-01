#include <vector>
#include <algorithm>

class Solution {
private:
    struct TimeSeatsPair {
        int time;
        int seats;

        TimeSeatsPair(int time, int seats) {
            this->time = time;
            this->seats = seats;
        }
    };

public:
    std::vector<int> corpFlightBookings(std::vector<std::vector<int>>& bookings, int n) {
        const int bookingsSize = bookings.size();
        std::vector<TimeSeatsPair> events;
        events.reserve(bookingsSize * 2);

        for(int i = 0; i < bookingsSize; i++) {
            events.push_back(TimeSeatsPair(bookings[i][0], bookings[i][2]));
            events.push_back(TimeSeatsPair(bookings[i][1] + 1, -bookings[i][2]));
        }

        std::sort(events.begin(), events.end(), [](const TimeSeatsPair& a, const TimeSeatsPair& b) {
            return a.time < b.time;
        });

        std::vector<int> result(n);
        int currentSeats = 0;
        int eventIndex = -1;
        for(int i = 0; i<n; i++) {
            while(eventIndex + 1 < events.size() && events[eventIndex + 1].time - 1 <= i) {
                eventIndex++;
                currentSeats+=events[eventIndex].seats;
            }
            result[i] = currentSeats;
        }

        return result;
    }
};