<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <#list trades as trade>
            ${trade.tradeDate}, ${trade.id}, ${trade.seller.fio}<br>
        </#list>

        </div>
    </div>
</main>

<#include "menu.ftl">
<#include "footer.ftl">