<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

        <div class="m-4 p-3 width-medium ">
            <div class="dashboard-content border-dashed p-3 m-4">
                <div class="row border-bottom border-3 p-1 m-1">
                    <div class="col noPadding">
                        <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
                    </div>
                    <div class="col d-flex justify-content-end mb-2 noPadding">
                        <a href="/app/plan/list" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
                    </div>
                </div>

                <div class="schedules-content">
                    <div class="schedules-content-header">
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    <c:out value="${list[0].plan_name}"/>
                                </p>
                            </div>
                        </div>
                        <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                            <div class="col-sm-10">
                                <p class="schedules-text">
                                    <c:out value="${list[0].plan_description}"/>
                                </p>
                            </div>
                        </div>
                    </div>

                    <c:forEach items="${days}" var="day" varStatus="loop">
                        <c:set var="dayId" value="${loop.count}"/>
                    <table class="table">
                        <thead>
                        <tr class="d-flex">
                            <th class="col-2">${day.name}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                            <tbody class="text-color-lighter">
                                <c:forEach items="${list}" var="listItem">
                                    <c:if test="${listItem.day_id == dayId}">
                                        <tr class="d-flex">
                                            <td class="col-2">${listItem.meal_name}</td>
                                            <td class="col-7">${listItem.recipe_description}</td>
                                            <td class="col-1 center">
                                                <a href="/app/plan/recipe/delete?id=${listItem.recipe_plan_id}&planid=${selectedPlanId}" class="btn btn-danger rounded-0 text-light m-1" onclick="return confirm('Czy na pewno chcesz usunąć przepis z planu?');">Usuń</a>
                                            </td>
                                            <td class="col-2 center">
                                                <a href="/app/recipe/details?id=${listItem.recipe_id}" class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</section>

<%@include file="/app/footer.jsp" %>