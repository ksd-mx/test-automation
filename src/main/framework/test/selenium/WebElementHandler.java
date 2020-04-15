package main.framework.test.selenium;

import main.framework.data.IDataReader;
import main.framework.test.mappings.WebElementMapping;
import main.framework.test.model.Result;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class WebElementHandler {
    private static final String LOCALE_COUNTRY = "BR";
    private static final String LOCALE_LANGUAGE = "pt";
    private static final String VALUE_ATTRIBUTE = "value";
    private static final String INNER_HTML_ATTRIBUTE = "innerHTML";

    private WebDriverManager webDriverManager;
    private WebElementMapping mapping;
    private WebElement element;
    private String valueKey;

    WebElementHandler(WebDriverManager webDriverManager, WebElementMapping mapping, WebElement element, String valueKey) {
        this.initialize(webDriverManager, mapping, element, valueKey);
    }

    private void initialize(WebElementHandler existingInstance) {
        this.initialize(
                existingInstance.webDriverManager,
                existingInstance.mapping,
                existingInstance.element,
                existingInstance.valueKey);
    }

    private void initialize(
            WebDriverManager webDriverManager,
            WebElementMapping webElementMapping,
            WebElement element,
            String valueKey) {
        this.webDriverManager = webDriverManager;
        this.mapping = webElementMapping;
        this.element = element;
        this.valueKey = valueKey;
    }

    public void switchTo() {
        this.webDriverManager.switchTo(this.element);
    }

    public boolean confirmValue() {
        return this.confirmValue(null);
    }

    public boolean confirmValue(String valueKey) {
        return this.confirmValue(valueKey, new Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY));
    }

    public boolean confirmValue(String valueKey, Locale locale) {
        if (this.exists()) {
            String value = valueKey == null ?
                    this.mapping.getValue().toLowerCase(locale) :
                    this.mapping.getValue(valueKey);
            boolean result = this.element.getText()
                    .toLowerCase(locale)
                    .equals(value.toLowerCase(locale));
            System.out.printf("ELEMENT [%s] VALUE ['%s'] CONFIRMATION: %s./n",
                            this.mapping.getKey(),
                            value,
                            result ? Result.PASSED : Result.FAILED);
            return result;
        } else
            return false;
    }

    public void click() {
        this.click(false);
    }

    public void click(boolean refresh) {
        if (this.exists(refresh))
            this.element.click();
    }

    public boolean exists() throws AssertionError {
        return this.exists(false);
    }

    public boolean exists(boolean refresh) {
        try {
            if (refresh)
                this.initialize(this.webDriverManager.getWebElement(this.mapping.getKey()));

            boolean result = this.element != null && this.element.isDisplayed();
            System.out.printf("ELEMENT [%s] EXISTENCE CONFIRMATION: %s./n",
                    this.mapping.getKey(),
                    result ? Result.PASSED : Result.FAILED);
            return result;
        } catch (Exception e){
            return false;
        }
    }

    public void sendKeys(Keys keys) {
        if (this.exists())
            this.element.sendKeys(keys);
    }

    public void sendKeys(CharSequence... value) {
        if (this.exists())
            this.element.sendKeys(value);
    }

    public void submit() {
        if (this.exists())
            this.element.submit();
    }

    public void clear() {
        if (this.exists())
            this.element.clear();
    }

    public void erase() {
        if (this.exists()) {
            int length = this.element.getText().length();
            while (length > 0) {
                this.element.sendKeys(Keys.BACK_SPACE);
                this.webDriverManager.pause();
                length--;
            }
        }
    }

    public String getTagName() {
        if (this.exists())
            return this.element.getTagName();

        return null;
    }

    public String getAttribute(String attribute) {
        if (this.exists())
            return this.element.getAttribute(attribute);

        return null;
    }

    public boolean hasAttribute(String attribute) {
        return this.getAttribute(attribute) != null;
    }

    public boolean isSelected() {
        if (this.exists())
            return this.element.isSelected();

        return false;
    }

    public boolean isEnabled() {
        if (this.exists())
            return this.element.isEnabled();

        return false;
    }

    public void resetValue() {
        this.resetValue(this.valueKey == null ? null : this.valueKey);
    }

    public void resetValue(String valueKey) {
        this.clear();
        this.sendKeys(valueKey == null ?
                this.mapping.getValue() :
                this.mapping.getValue(valueKey));
    }

    public String getMappedValue() {
        return this.getMappedValue(this.valueKey == null ? null : this.valueKey);
    }

    public String getMappedValue(String valueKey) {
        if (this.exists())
            return this.mapping.getValue(valueKey);
        return "";
    }

    public void updateMappingValue() {
        this.getMappedValue(this.valueKey == null ? null : this.valueKey);
    }

    public void updateMappingValue(String valueKey) {
        if (this.exists())
            this.mapping.setValue(this.element.getText());
    }

    public String getText() {
        if (this.exists())
            return this.element.getText();

        return "";
    }

    public String getValue() {
        String value = this.getAttribute(VALUE_ATTRIBUTE);

        return value == null ? "" : value;
    }

    public String getInnerHtml() {
        String value = this.getAttribute(INNER_HTML_ATTRIBUTE);

        return value == null ? "" : value;
    }

    public List<WebElementHandler> getWebElements(String selectorKey) {
        if (this.exists())
            return this.webDriverManager.getWebElements(this.mapping.getKey(), this.valueKey, selectorKey);

        return new ArrayList<>();
    }

    public boolean isDisplayed() {
        if (this.exists())
            return this.element.isDisplayed();

        return false;
    }

    public Point getLocation() {
        if (this.exists())
            return this.element.getLocation();

        return null;
    }

    public Dimension getSize() {
        if (this.exists())
            return this.element.getSize();

        return null;
    }

    public Rectangle getRect() {
        if (this.exists())
            return this.element.getRect();

        return null;
    }

    public String getCssValue(String value) {
        if (this.exists())
            return this.element.getCssValue(value);

        return null;
    }

    public void scrollTo() {
        this.webDriverManager.executeScript("arguments[0].scrollIntoView(true);", this.element);
    }

    public void waitUntilVisible() {
        this.webDriverManager.waitFor(ExpectedConditions.invisibilityOf(this.element));
    }

    public void waitUntilAttributeIs(String attribute, String value) {
        this.webDriverManager.waitFor(ExpectedConditions.attributeContains(this.element, attribute, value));
    }

    public void waitUntilStale() {
        this.webDriverManager.waitFor(ExpectedConditions.stalenessOf(this.element));
    }

    public void waitUntilTextInValue() {
        this.webDriverManager.waitFor(ExpectedConditions.textToBePresentInElementValue(this.element, this.mapping.getValue()));
    }

    public void waitUntilInvisible() {
        this.webDriverManager.waitFor(ExpectedConditions.invisibilityOf(this.element));
    }

    public void assertExists() throws AssertionError {
        this.assertExists(false);
    }

    public void assertExists(boolean refresh) throws AssertionError {
        String message = String.format("ELEMENT [%s] NOT FOUND.", this.mapping.getKey());
        boolean result = this.exists(refresh);
        Assert.assertTrue(message, result);
    }

    public void assertValueMatch() {
        this.assertValueMatch(this.mapping.getValue());
    }

    public void assertValueMatch(String expectedValue) throws AssertionError {
        String message = String.format("ELEMENT [%s] VALUE/TEXT DOES NOT MATCH: %s",
                this.mapping.getKey(),
                expectedValue);
        boolean result = this.getText().equals(expectedValue) || this.getValue().equals(expectedValue);
        Assert.assertTrue(message, result);
    }
}