<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <table class="table table-hover">
            <thead>
            <tr>
                <th>region</th>
                <th>street</th>
                <th>homeNumber</th>
                <th>flatNumber</th>
            </tr>
            </thead>
            <tbody>
            <#list addresses as address>
            <tr>
                <td>${address.region}</td>
                <td>${address.street}</td>
                <td>${address.homeNumber}</td>
                <td>${address.flatNumber}</td>
                <td>
                    <div class="btn-group">

                        <a class="btn btn-default" role="button"
                           href="address/edit/${address.id}">Edit</a>

                        <a class="btn btn-default" role="button"
                            onclick="deleteAndRefresh(${address.id}, '/address')">
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