package com.alibou.security.project.image.utils;

import com.alibou.security.project.baza.model.enums.PolishShops;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseText {
    private static final String REGEX_DATE = "\\d{4}-\\d{2}-\\d{2}";
    private static final String REGEX_MONEY = "\\d{1,4}(?:,\\d{2}|\\.\\d{2})";
    private static final String DIFFERENT_SHOP = "KAUFLAND";
    private static final LocalDate MOCKED_DATE = LocalDate.of(2023, 6, 9);

    public static BigDecimal getAmountOfMoney(String input) {
        List<BigDecimal> amounts = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_MONEY);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            BigDecimal amount = new BigDecimal(match.replace(",", "."));
            amounts.add(amount);
        }
        if (amounts.isEmpty()) {
            return BigDecimal.ZERO;
        }
        BigDecimal maxAmount = amounts.get(0);
        for (BigDecimal amount : amounts) {
            if (amount.compareTo(maxAmount) > 0) {
                maxAmount = amount;
            }
        }
        return maxAmount;
    }

    public static LocalDate getDate(String input) {
        List<LocalDate> dates = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_DATE);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String match = matcher.group();
            LocalDate date = LocalDate.parse(match);
            if (!dates.contains(date)) {
                dates.add(date);
            }
        }
        if (!dates.isEmpty()) {
            return dates.get(0);
        }
        return MOCKED_DATE;
    }

    public static String getShopName(String input) {
        String normalizedInput = input.toLowerCase();
        for (PolishShops shop : PolishShops.values()) {
            if (normalizedInput.contains(shop.toString().toLowerCase())) {
                return shop.toString();
            }
        }
        return DIFFERENT_SHOP;
    }
}
