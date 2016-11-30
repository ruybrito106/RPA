package steps

import rpa.Article
import rpa.ArticleController
import rpa.GoogleScholarService
import rpa.Researcher
import rpa.ResearcherController

/**
 * Created by rbb3 on 05/11/16.
 */
class CitationTestSteps {

    static public int findCitations(Article article) {
        GoogleScholarService gs = new GoogleScholarService()
        List<Article> list = new ArrayList<Article>()
        list.add(article)
        gs.findCitations(list)
        return list.get(0).citationAmount
    }

    static public int findCitationsResearcher(Researcher researcher) {
        GoogleScholarService gs = new GoogleScholarService()
        List<Article> list = new ArrayList<Article>()
        researcher.articles.each { article ->
            list.add(article)
        }
        def totalCitations = gs.findCitations(list)
        researcher.citationAmount = totalCitations
        def cont = new ResearcherController()
        cont.save(researcher)
        cont.response.reset()
        return totalCitations
    }

    static public boolean informationStored(String title, int citations) {
        Article art = Article.findByTitle(title)
        return art.citationAmount == citations
    }

    static public boolean informationStoredResearcher(String name, int citations) {
        Researcher res = Researcher.findByName(name)
        return res.citationAmount == citations
    }

}
