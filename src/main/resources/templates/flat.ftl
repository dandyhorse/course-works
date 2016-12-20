<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <div class="page-header">
            <h4>Flats</h4>
        </div>

        <a href="/flat/add" class="btn btn-success" role="button">Add record</a>

        <table class="table table-hover">
            <thead>
            <tr>
                <th>street</th>
                <th>typeHome</th>
                <th>commonSpace</th>
                <th>residentSpace</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <#list flats as flat>
            <tr>
                <td>${flat.address.street}</td>
                <td>${flat.typeHome}</td>
                <td>${flat.commonSpace}</td>
                <td>${flat.residentSpace}</td>
                <td>
                    <div class="btn-group">

                        <a class="btn btn-default" role="button"
                           href="flat/edit/${flat.id}">Edit</a>

                        <a class="btn btn-default" role="button"
                            onclick="deleteAndRefresh(${flat.id}, '/flat')">
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