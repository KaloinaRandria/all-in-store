package mg.working.allinstore.controller;

import lombok.RequiredArgsConstructor;
import mg.working.allinstore.model.dto.article.ArticleRequestDTO;
import mg.working.allinstore.model.dto.article.ArticleResponseDTO;
import mg.working.allinstore.service.article.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/save")
    public ResponseEntity<ArticleResponseDTO> create(@RequestBody ArticleRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(articleService.createArticle(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDTO> getById(@PathVariable String id) {
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ArticleResponseDTO>> getAll() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ArticleResponseDTO> update(@PathVariable String id,
                                                     @RequestBody ArticleRequestDTO dto) {
        return ResponseEntity.ok(articleService.updateArticle(id, dto));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }

}
