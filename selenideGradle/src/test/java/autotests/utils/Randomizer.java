package autotests.utils;

public class Randomizer {
    public String getRandomString(int length){
        String symbolsLibrary = "abcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder randomString = new StringBuilder(length);

        for ( int i=0; i<length; i++) {
            //generating a random number using math.random()
            int charIndex = (int)(symbolsLibrary.length() * Math.random());

            //adding character on 'charIndex' position to 'randomString'
            randomString.append(symbolsLibrary.charAt(charIndex));
        }

        return randomString.toString();
    }

    public String generatingEmailAddress(int leftPartLength){
        String email;
        email = getRandomString(leftPartLength) + "@gmail.com";
        return email;
    }
}
