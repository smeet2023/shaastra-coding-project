package com.shaastra.validation;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.shaastra.enums.Branch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TpIdValidator implements ConstraintValidator<ValidTpId, String> {

    private final EnumSet<Branch> validBranches = EnumSet.allOf(Branch.class);

    @Override
    public void initialize(ValidTpId constraintAnnotation) {
    }

    @Override
    public boolean isValid(String tpId, ConstraintValidatorContext context) {
        List<String> errors = new ArrayList<>();

        if (tpId == null) {
            errors.add("!! -> tp_id cannot be null");
            addErrorsToContext(errors, context);
            return false;
        }

        // Pattern to match tp_id structure: STARTYEAR-BRANCHDIVISIONROLLNO-ENDYEAR
        String regex = "^(\\d{2})-([A-Z]+)([A-G])(\\d{2})-(\\d{2})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(tpId);

        if (!matcher.matches()) {
        	errors.add("!! --> tp_id must match the pattern: StartYear-BranchDivisionRollNo-EndYear");
        } else {
            try {
		        int startYear = Integer.parseInt(matcher.group(1));
		        String branch = matcher.group(2);
		        String division = matcher.group(3);
		        int rollNo = Integer.parseInt(matcher.group(4));
		        int endYear = Integer.parseInt(matcher.group(5));
                // Only perform detailed checks if pattern matches

                // 1. Check that the end year is exactly start year + 4
                if (endYear != startYear + 4) {
                    errors.add("!! -> End year must be 4 years after the starting year");
                }

                // 2. Check if branch exists in our EnumSet `validBranches`
                if (!validBranches.contains(Branch.valueOf(branch))) {
                    errors.add("!! -> Invalid branch: " + branch);
                }

                // 3. Check if division is within A-G
                if (!division.matches("[A-G]")) {
                    errors.add("!! -> Division must be a single letter from A to G");
                }

                // 4. Check if roll number is between 1 and 100
                if (rollNo < 1 || rollNo > 100) {
                    errors.add("!! -> Roll number must be between 1 and 100");
                }
            } catch (Exception e) {
                errors.add("!! -> Please check your tp_id , some data might be wrong");
            }
        }

        // Add all collected errors to context if any are found
        if (!errors.isEmpty()) {
            addErrorsToContext(errors, context);
            return false;
        }

        return true; // All checks passed
    }

    // Helper method to add errors to the context
    private void addErrorsToContext(List<String> errors, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        for (String error : errors) {
            context.buildConstraintViolationWithTemplate(error).addConstraintViolation();
        }
    }
}
