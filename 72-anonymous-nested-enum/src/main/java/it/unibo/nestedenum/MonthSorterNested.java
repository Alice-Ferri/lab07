package it.unibo.nestedenum;

import java.util.Comparator;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {

    public enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private final int monthDays;

        Month(int monthDays) {
            this.monthDays = monthDays;
        }

        public static Month fromString(String monthName){
            
            Month monthChoosed = null;

            for(final Month elem : Month.values()){
                if(elem.name().equalsIgnoreCase(monthName)){
                    monthChoosed = elem;
                }
                else if(elem.name().startsWith(monthName.toUpperCase())){
                    if(monthChoosed != null){
                        throw new IllegalArgumentException();
                    }
                    monthChoosed = elem;
                }
            }
            return monthChoosed;
        }
    }
    
    @Override
    public Comparator<String> sortByDays() {
        return new SortByDate();
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new SortByMonthOrder(); 
    }

    private class SortByMonthOrder implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).ordinal(),
                                    Month.fromString(o2).ordinal());
        }
    }
   
    private class SortByDate implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return Integer.compare(Month.fromString(o1).monthDays,
                                    Month.fromString(o2).monthDays);
        }
    }
    
}
