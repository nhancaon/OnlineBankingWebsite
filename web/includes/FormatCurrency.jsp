<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.Locale"%>

<%!
    // Function to format currency
    public String formatCurrency(Double amount) {
        Locale locale = new Locale("en", "US");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        // Change the decimal separator from '.' to ','
        if (currencyFormatter instanceof DecimalFormat) {
            DecimalFormat decimalFormat = (DecimalFormat) currencyFormatter;
            decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("en", "US")));
        }
        String formattedAmount = currencyFormatter.format(amount);
        // Remove the '.00' at the end
        formattedAmount = formattedAmount.replaceAll("\\.00$", "");
        formattedAmount = formattedAmount.replace("$", "");
        
        return formattedAmount;
    }
%>
