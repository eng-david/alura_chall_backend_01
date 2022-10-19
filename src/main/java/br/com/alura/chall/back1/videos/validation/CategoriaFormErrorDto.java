package br.com.alura.chall.back1.videos.validation;

public class CategoriaFormErrorDto {

    private String field;
    private String errorMessage;

    public CategoriaFormErrorDto(String field, String errorMessage) {
        this.field = field;
        this.errorMessage = errorMessage;
    }

    public String getField() {
        return field;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
