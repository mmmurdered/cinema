package com.murdered.cinema.util;

import com.murdered.cinema.dto.SessionDTO;
import com.murdered.cinema.model.Session;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SessionSortUtil {

    public static void SortByAvailablePlaces(List<Session> dtoList){
        Collections.sort(dtoList, sessionComparator);
    }

    public static Comparator<Session> sessionComparator = new Comparator<Session>(){

        @Override
        public int compare(Session o1, Session o2) {
            int place1 = o1.getAvailablePlaces();
            int place2 = o2.getAvailablePlaces();

            return place2 - place1;
        }
    };
}
