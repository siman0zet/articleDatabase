package ru.pnu.edu.articledatabase.enumerations;

public enum  ScienceArea {
    PHISICS_MATH("01.00.00 физико-математические науки"),
            CHEMISTRY("02.00.00 химические науки"),
            BIOLOGY("03.00.00 биологические науки"),
            TECHNOLOGY("05.00.00 технические науки"),
            AGRICULTURE("06.00.00 сельскохозяйственные науки"),
            HUMANITARIANISM("07.00.00 гуманитарные науки"),
            ECONOMY("08.00.00 экономические науки"),
            PHILOSOPHY("09.00.00 философские науки"),
            PHILOLOGY("10.00.00 филологические науки"),
            LAW("12.00.00 юридические науки"),
            TEACHER("13.00.00 педагогические науки"),
            MEDICINE("14.00.00 медицинские науки"),
            ART("17.00.00 искусствоведение"),
            PSYCHOLOGY("19.00.00 психологические науки"),
            SOCIOLOGY("22.00.00 социологические науки"),
            POLITICAL("23.00.00 политология"),
            CULTURE("24.00.00 культурология"),
            SCIENCE_OF_EARTH("25.00.00 науки о Земле");

        private String label;

        ScienceArea(String label){
                this.label = label;
        }

        public String toString(){
                return label;
        }
}
