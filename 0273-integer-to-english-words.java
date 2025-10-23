class Solution {
    final String[] NUM_WORD = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

    final String[] TEEN_WORD = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen",};

    final String[] DEC_WORD = new String[]{"Zero", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety",};

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        final var builder = new StringBuilder();
        if (num >= 1000_000_000) {
            final var billions = num / 1000_000_000;
            builder.append(upToHundredToWords(billions));
            builder.append(" Billion ");

            num %= 1000_000_000;
        }

        if (num >= 1000_000) {
            final var milions = num / 1000_000;
            builder.append(upToThousandToWords(milions));
            builder.append(" Million ");

            num %= 1000_000;
        }

        if (num >= 1000) {
            final var thousands = num / 1000;
            builder.append(upToThousandToWords(thousands));
            builder.append(" Thousand ");

            num %= 1000;
        }

        builder.append(upToThousandToWords(num));
        return builder.toString().trim().replaceAll("\s+", " ");
    }

    private String upToThousandToWords(int num) {
        final var hundreds = num / 100;
        if (hundreds == 0) {
            return upToHundredToWords(num);
        }

        final var builder = new StringBuilder();
        builder.append(NUM_WORD[hundreds]);
        builder.append(" Hundred ");
        builder.append(upToHundredToWords(num % 100));
        return builder.toString();
    }

    private String upToHundredToWords(int num) {
        if (num < 10) {
            return NUM_WORD[num];
        }

        if (num < 20) {
            return TEEN_WORD[num - 10];
        }

        final var decimals = num / 10;
        final var rest = num % 10;
        if (rest != 0) {
            return DEC_WORD[decimals] + ' ' + NUM_WORD[rest];
        } else {
            return DEC_WORD[decimals];
        }
    }
}