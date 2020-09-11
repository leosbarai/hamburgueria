package com.github.leosbarai.hamburgueria.config;

import org.passay.AlphabeticalCharacterRule;
import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class PasswordCheck {

    private static final int MINIMUM_SIZE = 6;
    private static final int MAXIMUM_SIZE = 10;
    private static final int DIGITS = 1;
    private static final int ALPHABET = 1;

    public void checkPasswordRules(String password) throws Exception {
        if (StringUtils.isEmpty(password)) {
            throw new Exception("Password must be informed.");
        }

        LengthRule lengthRule = new LengthRule(MINIMUM_SIZE, MAXIMUM_SIZE);

        WhitespaceRule whitespaceRule = new WhitespaceRule();

        AlphabeticalCharacterRule alphabeticalCharacterRule = new AlphabeticalCharacterRule(ALPHABET);

        DigitCharacterRule digitCharacterRule = new DigitCharacterRule(DIGITS);

        List<Rule> ruleList = new ArrayList<Rule>();
        ruleList.add(lengthRule);
        ruleList.add(whitespaceRule);
        ruleList.add(alphabeticalCharacterRule);
        ruleList.add(digitCharacterRule);

        PasswordValidator validator = new PasswordValidator(ruleList);
        PasswordData passwordData = new PasswordData(password);

        RuleResult result = validator.validate(passwordData);

        List<String> msg = validator.getMessages(result);

        if (!result.isValid()) {
            throw new Exception(msg.toString());
        }
    }
}
