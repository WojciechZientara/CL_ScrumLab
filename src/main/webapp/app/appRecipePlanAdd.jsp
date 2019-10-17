<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
            </div>
        </div>

        <c:if test="${not empty planList}">
            <c:if test="${not empty recipeList}">
            <div class="schedules-content">
            <form action="/app/recipe/plan/add" method="post">
                <div class="form-group row">
                    <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                        Wybierz plan
                    </label>
                    <div class="col-sm-3">
                        <select class="form-control" id="choosePlan" name="plan_id">
                            <c:forEach items="${planList}" var="planList">
                                <option value="${planList.id}">${planList.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 label-size col-form-label">
                        Nazwa posiłku
                    </label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" value="" id="name" name="meal_name"
                               placeholder="Nazwa posiłku">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="number" class="col-sm-2 label-size col-form-label">
                        Numer posiłku
                    </label>
                    <div class="col-sm-2">
                        <input type="text" class="form-control" value="" id="number" name="display_order"
                               placeholder="Numer posiłki">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="recipe" class="col-sm-2 label-size col-form-label">
                        Przepis
                    </label>
                    <div class="col-sm-4">
                        <select class="form-control" id="recipe" name="recipe_id">
                            <c:forEach items="${recipeList}" var="recipeList">
                                <option value="${recipeList.id}">${recipeList.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="day" class="col-sm-2 label-size col-form-label">
                        Dzień
                    </label>
                    <div class="col-sm-2">
                        <select class="form-control" id="day" name="day_name_id">
                            <option value="1">poniedziałek</option>
                            <option value="2">wtorek</option>
                            <option value="3">środa</option>
                            <option value="4">czwartek</option>
                            <option value="5">piątek</option>
                            <option value="6">sobota</option>
                            <option value="7">niedziela</option>
                        </select>
                    </div>
                </div>
                <div class="col d-flex justify-content-start mb-2 noPadding">
                    <button type="submit" class="btn btn-success rounded-0 p-2 pl-4 pr-4">Wyślij</button>
                </div>
            </form>
        </div>
        </c:if>
        </c:if>

        <c:if test="${empty planList}">
            <div class="row border-bottom border-2 p-1 m-2">
                <div class="col noPadding">
                    <h3 class="text-uppercase">Dodaj proszę przynajmniej jeden plan.</h3>
                </div>
            </div>
        </c:if>

        <c:if test="${empty recipeList}">
            <div class="row border-bottom border-2 p-1 m-2">
                <div class="col noPadding">
                    <h3 class="text-uppercase">Dodaj proszę przynajmniej jeden przepis.</h3>
                </div>
            </div>
        </c:if>

    </div>
</div>

<%@include file="footer.jsp" %>
