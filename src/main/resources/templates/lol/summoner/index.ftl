<#import "/templates/base/baseLayout.ftl" as base/>
<@base.m_html title="Home" tab_index=0>
    <form class="input-group col-md-4 col-md-offset-4" action="/lol/summoner/search">
        <input type="text" name="name" class="form-control" placeholder="Summoner's name" required/ >
        <span class="input-group-btn">
               <button class="btn btn-info">Search</button>
        </span>
    </form>
    <script src="/static/js/lol/summonerIndex.js"></script>
</@base.m_html>