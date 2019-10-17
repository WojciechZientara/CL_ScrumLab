<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="m-4 p-4 width-medium">
            <div class="dashboard-header m-4">
                <div class="dashboard-menu">
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj plan</span>
                        </a>
                    </div>
                    <div class="menu-item border-dashed">
                        <a href="/app/recipe/plan/add">
                            <i class="far fa-plus-square icon-plus-square"></i>
                            <span class="title">dodaj przepis do planu</span>
                        </a>
                    </div>
                </div>

                <div class="dashboard-alerts">
                    <div class="alert-item alert-info">
                        <i class="fas icon-circle fa-info-circle"></i>
                        <span class="font-weight-bold">Liczba przepisów: <c:out value="${adminRecipes}"
                                                                                default="Brak"/></span>
                    </div>
                    <div class="alert-item alert-light">
                        <i class="far icon-calendar fa-calendar-alt"></i>
                        <span class="font-weight-bold">Liczba planów: <c:out value="${adminPlans}"
                                                                             default="Brak"/></span>
                    </div>
                </div>
            </div>

            <div class="m-4 p-4 border-dashed">

                <h2 class="dashboard-content-title">
                    <span>Ostatnio dodany plan:</span> <c:out value="${lista[0].plan_name}" default="Brak"/>
                </h2>

                <c:if test="${not empty lista[0].plan_name}">

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Poniedziałek</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 1}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Wtorek</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 2}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Środa</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 3}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Czwartek</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 4}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Piątek</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 5}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Sobota</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 6}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">Niedziela</th>
                            <th class="col-8"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${lista}" var="lista">
                            <tr class="d-flex">
                                <c:if test="${lista.day_id == 7}">
                                    <td class="col-12">
                                        <div class="inner-wrap d-flex">
                                            <div class="col-2 meal">${lista.meal_name}</div>
                                            <div class="col-8 recipe_name">${lista.recipe_name}</div>
                                            <div class="col-2 button-wrap">
                                                <button type="button"
                                                        class="btn btn-primary rounded-0 btn-recipe-details">
                                                    Szczegóły
                                                </button>
                                            </div>
                                        </div>
                                        <div class="recipe_description"
                                             style="display: none;">${lista.recipe_description}</div>
                                    </td>

                                </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>

                </c:if>

            </div>

        </div>



<%@include file="footer.jsp" %>