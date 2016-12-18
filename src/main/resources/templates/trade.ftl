<#include "header.ftl">
<main>
    <div id="content">
        <div class="innertube">

        <#list trades as trade>
            <EntityLocation>
                <nc:Location id="${trade.tradeDate}" dataid="${trade.id}">
                </nc:Location>
            </EntityLocation>
            <br>
        </#list>

        </div>
    </div>
</main>

<#include "menu.ftl">
<#include "footer.ftl">