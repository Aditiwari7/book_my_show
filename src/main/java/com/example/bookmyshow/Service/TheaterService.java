package com.example.bookmyshow.Service;

import com.example.bookmyshow.Dtos.TheaterRequestDto;
import com.example.bookmyshow.Enums.SeatType;
import com.example.bookmyshow.Models.TheaterEntity;
import com.example.bookmyshow.Models.TheaterSeatEntity;
import com.example.bookmyshow.Repository.TheaterRepository;
import com.example.bookmyshow.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String createTheater(TheaterRequestDto theaterRequestDto){
        TheaterEntity theater = TheaterEntity.builder().city(theaterRequestDto.getCity()).name(theaterRequestDto.getName()).address(theaterRequestDto.getAddress()).build();

        List<TheaterSeatEntity> theaterSeats = createTheaterSeats();
        theater.setTheaterSeatEntityList(theaterSeats);

        for (TheaterSeatEntity theaterSeat : theaterSeats){
            theaterSeat.setTheater(theater);
        }
        theaterRepository.save(theater);
        return "Theater Added Successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(){
        List<TheaterSeatEntity> seats = new ArrayList<>();

        for(int i=0; i<5; i++){
            char ch = (char)('A'+i);
            String seatNo = "1"+ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.CLASSIC, 100);
            seats.add(theaterSeat);
        }

        for (int i=0; i<5; i++){
            char ch = (char)('A'+i);
            String seatNo = "2"+ch;
            TheaterSeatEntity theaterSeat = new TheaterSeatEntity(seatNo, SeatType.PLATINUM, 200);
            seats.add(theaterSeat);
        }

        theaterSeatRepository.saveAll(seats);
        return seats;
    }
}
