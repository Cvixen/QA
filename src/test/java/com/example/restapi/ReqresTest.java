package com.example.restapi;

import org.junit.Assert;
import org.junit.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    /**
     * Получить список пользователей и убедиться, что имена файлов-аватаров
     * пользователей совпадает; Убедиться, что email пользователй имеет окончание
     * reqres.in
     */
    @Test
    public void checkAvatarAndIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);
        users.forEach(user -> Assert.assertTrue(user.getAvatar().contains(user.getId().toString())));
        Assert.assertTrue(users.stream().allMatch(user -> user.getEmail().endsWith("@reqres.in")));
    }

    /**
     * Проверка на успешную регистрацию
     */
    @Test
    public void successRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());
        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }

    /**
     * Проверка на неуспешную регистрацию (отсутствует пароль)
     */
    @Test
    public void failRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());
        Register user = new Register("sydney@fife", "");
        FailReg failReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(FailReg.class);
        Assert.assertEquals("Missing password", failReg.getError());
    }

    /**
     * Проверить, что операция List<Resource> возращяет данные,
     * отсортированные по годам.
     */
    @Test
    public void sortedYearsTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
    }

    /**
     * Удалить второго пользователяи сравнить статус-код
     */
    @Test
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUnique(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    /**
     * Обновить информацию о пользователе и сравнить дату обновления
     * с текущей датой на машине.
     */
    @Test
    public void timeTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);
        String currentTime = Clock.systemUTC().instant().toString().replaceAll("(.{8})$", "");
        ;
        Assert.assertEquals(currentTime, response.getUpdateAt().replaceAll("(.{5})$", ""));
    }

}
