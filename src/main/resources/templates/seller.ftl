<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">
            <#list sellers as seller>
                ${seller.id}, ${seller.fio}, ${seller.phoneNumber}<br>

            </#list>


        </div>
    </div>
</main>

<#include "menu.ftl">
<#include "footer.ftl">