package me.saniukvyacheslav.util.string.generation;

import lombok.Getter;
import lombok.Setter;
import me.saniukvyacheslav.util.MathUtils;

import java.util.Objects;

/**
 * StringsGenerator class instances used to generate random strings.
 * You can configure generator instance via "use*" setters methods.
 * You must redefine {@link StringsGenerator#getUsedLetters()} used letters strings in child class.
 * By default, generator will use ONLY lowercase letters.
 * You can specify used numbers (by default: 0123456789);
 * Ypu can specify any symbols (be default: !@#$%^&*()_-+=|);
 */
public class StringsGenerator {

    @Getter @Setter private String usedLetters = "";
    @Getter @Setter private String usedNumbers = "0123456789";
    @Getter @Setter private String usedSpecialSymbols = "!@#$%^&*()_-+=|";

    @Getter private boolean isUseLowerCaseLetters = false;
    @Getter private boolean isUseUpperCaseLetters = false;
    @Getter private boolean isUseNumbers = false;
    @Getter private boolean isUseSpecialSymbols = false;
    protected final StringBuilder symbolsSetBuilder = new StringBuilder();

    /**
     * Construct new instance.
     * @param aUsedLetters - used letters string.
     */
    public StringsGenerator(String aUsedLetters) {
        Objects.requireNonNull(aUsedLetters, "Used letters string must be not null.");
        this.setUsedLetters(aUsedLetters);
        this.useLowerCaseLetters(true);
    }

    /**
     * Enable/disable lowercase letters in generated strings.
     * @param isUse - enable/disable flag.
     */
    public void useLowerCaseLetters(boolean isUse) {
        this.isUseLowerCaseLetters = isUse;

        int index= this.symbolsSetBuilder.indexOf(this.usedLetters);
        if (isUse) {
            if (index == -1) this.symbolsSetBuilder.append(this.usedLetters);
        }else {
            if (index != -1) this.symbolsSetBuilder.delete(index, this.usedLetters.length());
        }

        this.symbolsSetBuilder.trimToSize();
    }

    /**
     * Enable/disable uppercase letters in generated strings.
     * @param isUse - enable/disable flag.
     */
    public void useUpperCaseLetters(boolean isUse) {
        this.isUseUpperCaseLetters = isUse;

        int index= this.symbolsSetBuilder.indexOf(this.usedLetters);
        if (isUse) {
            if (index == -1) this.symbolsSetBuilder.append(this.usedLetters);
        }else {
            if (index != -1) this.symbolsSetBuilder.delete(index, this.usedLetters.length());
        }

        this.symbolsSetBuilder.trimToSize();
    }

    /**
     * Enable/disable numbers in generated strings.
     * @param isUse - enable/disable flag.
     */
    public void useNumbers(boolean isUse) {
        this.isUseNumbers = isUse;

        int index= this.symbolsSetBuilder.indexOf(this.usedNumbers);
        if (isUse) {
            if (index == -1) this.symbolsSetBuilder.append(this.usedNumbers);
        }else {
            if (index != -1) this.symbolsSetBuilder.delete(index, this.usedNumbers.length());
        }

        this.symbolsSetBuilder.trimToSize();
    }

    /**
     * Enable/disable special symbols in generated strings.
     * @param isUse - enable/disable flag.
     */
    public void useSpecialSymbols(boolean isUse) {
        this.isUseSpecialSymbols = isUse;

        int index= this.symbolsSetBuilder.indexOf(this.usedSpecialSymbols);
        if (isUse) {
            if (index == -1) this.symbolsSetBuilder.append(this.usedSpecialSymbols);
        }else {
            if (index != -1) this.symbolsSetBuilder.delete(index, this.usedSpecialSymbols.length());
        }

        this.symbolsSetBuilder.trimToSize();
    }

    /**
     * Generate new string of specified length.
     * @param aLength - generated string length.
     * @return - generated string.
     */
    public String generate(int aLength) {
        if (aLength < 0) throw new IllegalArgumentException("Length of String must be positive or zero.");
        if (aLength == 0) return "";

        final String FINAL_SET = this.symbolsSetBuilder.toString();
        if (FINAL_SET.isEmpty()) throw new IllegalStateException("There are no available characters to generate string.");

        StringBuilder resultStringBuilder = new StringBuilder();
        for (int i=0; i<aLength; i++)
            resultStringBuilder.append(FINAL_SET.charAt(MathUtils.generateRandomIntFromRange(0, (FINAL_SET.length()-1))));

        return resultStringBuilder.toString();
    }
}
