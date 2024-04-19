package com.example.telegram.core.discover;

public enum Explanation {
    RESPONSE_1("Certainly! Honey has an incredible ability to resist " +
            "spoilage because of its low moisture content and acidic pH. " +
            "These conditions create an inhospitable environment for bacteria and microorganisms, " +
            "preventing them from thriving and causing the honey to spoil. " +
            "As a result, honey can last for thousands of years without going bad, as evidenced by archaeological" +
            " finds of perfectly edible honey in ancient Egyptian tombs."),
    RESPONSE_2("The Great Wall of China is not one continuous wall but a series of walls and fortifications" +
            " built over centuries by different Chinese dynasties. Initially created to defend against nomadic tribes" +
            " from the north, the walls were gradually connected and expanded. This resulted in the vast network known" +
            " today as the Great Wall of China."),
    RESPONSE_3("Bananas are classified as berries because they develop from a single flower's " +
            "ovary with seeds inside. However, strawberries, despite their name, aren't true berries because" +
            " they form from a flower with multiple ovaries, each producing seeds on the outside of the fruit."),
    RESPONSE_4("The shortest war in history occurred between Britain and Zanzibar in 1896. " +
            "It lasted only 38 minutes. The conflict arose when Sultan Khalid bin Barghash of Zanzibar " +
            "refused to step down after Britain declared him unfit to rule. In response, British warships bombarded"+
            " the Sultan's palace, quickly forcing his surrender. This brief but decisive conflict marked the end " +
            "of Sultan Khalid's reign and remains a remarkable historical event for its brevity.");

    public String values;

    Explanation(String values) {
        this.values = values;
    }
}
