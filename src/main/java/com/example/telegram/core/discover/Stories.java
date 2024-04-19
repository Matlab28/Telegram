package com.example.telegram.core.discover;

public enum Stories {
    RESPONSE_1("Did you know that honey never spoils?" +
            "Archaeologists have found pots of honey in ancient Egyptian tombs that are over" +
            "3,000 years old and still perfectly edible." +
            "he key to this remarkable longevity is the unique combination of low moisture content and acidic pH," +
            "which create an environment where bacteria and microorganisms cannot thrive." +
            "So, if you ever find a jar of honey hidden in the back of your pantry," +
            "don't worry about it going bad – it'll likely be just as delicious as the day you bought it!"),
    RESPONSE_2("The Great Wall of China isn't a single continuous wall. Instead, it's a series of walls " +
            "and fortifications built by various Chinese dynasties over centuries. These structures were initially" +
            " constructed to protect against invasions and raids from nomadic tribes from the north. Over time, " +
            "the walls were connected, expanded, and reinforced, resulting in the massive network we know today" +
            " as the Great Wall of China."),
    RESPONSE_3("Bananas are berries, while strawberries aren't. " +
            "In botanical terms, a berry is a fruit produced from the ovary of a single flower with " +
            "seeds embedded in the flesh. " +
            "Bananas fit this definition perfectly, making them berries." +
            " On the other hand, strawberries, despite their name, " +
            "are not true berries because they form from a flower with multiple ovaries, " +
            "each producing a seed on the outside of the fruit."),
    RESPONSE_4("The shortest war in history lasted only 38 minutes. It occurred between the countries of " +
            "Britain and Zanzibar on August 27, 1896." +
            " The war began when Sultan Khalid bin Barghash of Zanzibar refused " +
            "to step down after Britain had declared him unfit to rule. In response, British warships bombarded" +
            " the Sultan's palace, quickly overwhelming his forces. " +
            "After just 38 minutes, Sultan Khalid surrendered, " +
            "marking the end of the shortest recorded war in history.");

    public String values;

    Stories(String values) {
        this.values = values;
    }
}
