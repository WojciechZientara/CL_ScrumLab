<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>

<div class="m-4 p-3 width-medium">
    <div class="m-4 p-3 border-dashed view-height">

        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">LISTA UŻYTKOWNIKÓW</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="/app" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <table class="table">
                <thead>

                <!-- FOR EACH -->

                <c:forEach items="${admins}" var="admin" varStatus="loopStatus">
                <tr class="d-flex">
                    <td class="col-1">${loopStatus.count}</td>
                    <td class="col-3">${admin.firstName}</td>
                    <td class="col-6">${admin.lastName}</td>
                    <td class="col-2 center">
                        <a href="#" class="btn btn-danger rounded-0 text-light m-1">Blokuj</a>
                    </td>
                </tr>
                </c:forEach>

                <!-- /FOR EACH -->

                </tbody>
            </table>

        </div>
    </div>
</div>

<%@include file="footer.jsp" %>