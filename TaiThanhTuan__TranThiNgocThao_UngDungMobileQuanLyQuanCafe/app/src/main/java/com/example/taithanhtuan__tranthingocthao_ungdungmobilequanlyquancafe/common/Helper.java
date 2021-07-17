package com.example.taithanhtuan__tranthingocthao_ungdungmobilequanlyquancafe.common;

public class Helper {
    public static boolean isFullname(String str) {
        String expression = "^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂẾưăạảấầẩẫậắằẳẵặẹẻẽềềểếỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s\\W|_]+$";
        return str.matches(expression);
    }
    public static boolean isPhoneNumber(String str) {
        String expression = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
        return str.matches(expression);
    }
    public static boolean isPassword(String str) {
        String expression = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return str.matches(expression);
    }
}
