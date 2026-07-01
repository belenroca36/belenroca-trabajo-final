package com.orangehrm.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.orangehrm.pages.DashboardPage;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMPage;
import com.orangehrm.utils.DriverManager;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlujoPrincipalSteps {

    private WebDriver driver() {
        return DriverManager.getDriver();
    }

    @Given("el usuario esta en la pagina de login")
    public void el_usuario_esta_en_la_pagina_de_login() {
        LoginPage loginPage = new LoginPage(driver());
        loginPage.goTo();
        Assert.assertTrue(loginPage.isOnLoginPage(), "No se cargo la pagina de login");
    }

    @When("inicia sesion con usuario {string} y contrasena {string}")
    public void inicia_sesion_con_usuario_y_contrasena(String usuario, String contrasena) {
        new LoginPage(driver()).loginAs(usuario, contrasena);
    }

    @Then("se muestra el Dashboard")
    public void se_muestra_el_dashboard() {
        Assert.assertTrue(new DashboardPage(driver()).isOnDashboard(),
                "No se muestra el Dashboard luego del login");
    }

    @Given("el usuario inicio sesion con usuario {string} y contrasena {string}")
    public void el_usuario_inicio_sesion_con_usuario_y_contrasena(String usuario, String contrasena) {
        LoginPage loginPage = new LoginPage(driver());
        loginPage.goTo();
        loginPage.loginAs(usuario, contrasena);
        Assert.assertTrue(new DashboardPage(driver()).isOnDashboard(),
                "El login previo no llego al Dashboard");
    }

    @When("navega al modulo PIM")
    public void navega_al_modulo_pim() {
        DashboardPage dashboardPage = new DashboardPage(driver());
        dashboardPage.goToPIM();
        Assert.assertTrue(new PIMPage(driver()).isOnPIMPage(), "No se llego al modulo PIM");
    }

    @When("busca el empleado {string}")
    public void busca_el_empleado(String nombre) {
        new PIMPage(driver()).searchEmployeeByName(nombre);
    }

    @Then("se muestran resultados de la busqueda")
    public void se_muestran_resultados_de_la_busqueda() {
        Assert.assertTrue(new PIMPage(driver()).hasResults(),
                "La busqueda no devolvio resultados");
    }

    @When("cierra sesion")
    public void cierra_sesion() {
        new DashboardPage(driver()).logout();
    }

    @Then("se muestra la pagina de login")
    public void se_muestra_la_pagina_de_login() {
        Assert.assertTrue(new LoginPage(driver()).isOnLoginPage(),
                "No se volvio a la pagina de login luego del logout");
    }
}
