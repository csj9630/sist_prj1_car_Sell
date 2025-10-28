package kr.co.sist.car_sell.function.UserMenu;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumberDocumentFilter extends DocumentFilter {
	private JTextField field;

    public NumberDocumentFilter(JTextField field) {
        this.field = field;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) {
                return; // 숫자가 아니면 무시
            }
        }
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                return; // 숫자가 아니면 무시
            }
        }
        super.replace(fb, offset, length, text, attrs);
    }
}//NumberDocumentFilter
