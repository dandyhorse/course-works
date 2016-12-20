<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <a href="/seller/add" class="btn btn-success" role="button">Add record</a>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>FIO</th>
                <th>Phone Number</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list sellers as seller>
            <tr>
                <td>${seller.fio}</td>
                <td>${seller.phoneNumber}</td>
                <td>
                    <div class="btn-group">

                        <a class="btn btn-default" role="button"
                           href="seller/edit/${seller.id}">Edit</a>

                        <a class="btn btn-default" role="button"
                            onclick="deleteAndRefresh(${seller.id}, '/seller')">
                                Delete
                        </a>
                    </div>
                </td>
            </tr>
            </#list>
            </tbody>
        </table>
        </div>
    </div>
</main>

<#include "menu.ftl">
<#include "footer.ftl">