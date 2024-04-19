package com.example.telegram.core.bookRec;

import lombok.Getter;

@Getter
public enum Genres {
    ROMANCE_1("- Twilight by Stephenie Meyer"),
    ROMANCE_2("- Halfway to the Grave by Jeaniene Frost"),
    ROMANCE_3("- Hopeless Colleen Hoover by Colleen Hoover"),
    ROMANCE_4("- Dark Lover by Jessica Bird"),
    ROMANCE_5("- The Hunger Games by Suzanne Collins"),
    ROMANCE_6("- Catching Fire by Suzanne Collins"),
    SCIENCE_FICTION_1("- The Diary of Young Girl by Anne Frank"),
    SCIENCE_FICTION_2("- DUNE by Frank Herdery"),
    SCIENCE_FICTION_3("- 1984 by George Orwell"),
    SCIENCE_FICTION_4("- 11/22/63 by Stephan King"),
    SCIENCE_FICTION_5("- World War Z by Max Brooks"),
    SCIENCE_FICTION_6("- The Stand by Stephen King"),
    NON_FICTION_1("- Guns, Germs, and Steel by Jared Diamond"),
    NON_FICTION_2("- War and Peace by Leo Tolstoy"),
    NON_FICTION_3("- Anna Karenina by Leo Tolstoy"),
    NON_FICTION_4("- A Story of Yesterday by Sergio Cobo"),
    NON_FICTION_5("- The Boy in Striped Pajamas by John Boyne"),
    NON_FICTION_6("- Truth by Aleatha Romig");

    private String values;

    Genres(String values) {
        this.values = values;
    }
}
