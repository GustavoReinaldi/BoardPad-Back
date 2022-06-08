package br.com.boardpadbackend.controllers;

import br.com.boardpadbackend.dto.CategoryDto;
import br.com.boardpadbackend.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "/category")
@RestController
@Api(value = "Task Categories", tags = {"Category"})
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation(value = "Lists all categories available")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 204, message = "No categories available"),
            @ApiResponse(code = 500, message = "Internal server error. Please try again")
    })
    public ResponseEntity<List<CategoryDto>> listAllCategories () {
        List<CategoryDto> responseList = categoryService.listAllCategories();

        return (responseList.size() > 0 ) ?
                ResponseEntity.ok().body(responseList) :
                ResponseEntity.noContent().build();
    }
    @ApiOperation("Create a new task Category")
    @ApiResponses({
            @ApiResponse( code= 201, message = "Category created successfully"),
            @ApiResponse( code= 500, message = "Internal Server Error. Category wasn't created.")
    })
    @PostMapping
    public ResponseEntity<CategoryDto> createCategory (String newCategoryName) {
        CategoryDto newCategory = categoryService.createCategory(newCategoryName);
        return (newCategory != null && !newCategory.getName().isEmpty()) ?
                ResponseEntity.status(201).body(newCategory) :
                ResponseEntity.status(500).build();
    }
    @ApiOperation("Delete a task Category")
    @ApiResponses({
            @ApiResponse( code= 200, message = "Category deleted successfully"),
            @ApiResponse( code= 500, message = "Internal Server Error. Category wasn't deleted.")
    })
    @DeleteMapping("{id}")
    public void deleteCategoryById (@PathVariable("id") Long idCategory) {
        categoryService.deleteCategoryById(idCategory);
    }

}