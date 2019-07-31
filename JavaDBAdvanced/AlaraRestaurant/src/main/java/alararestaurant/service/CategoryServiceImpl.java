package alararestaurant.service;

import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String exportCategoriesByCountOfItems() {
        StringBuilder sb = new StringBuilder();

        for (Category category : this.categoryRepository.findAllOrderByItemCount()) {
            sb.append(String.format("Category: %s", category.getName())).append(System.lineSeparator());

            for (Item item : category.getItems()) {
                sb.append(String.format("--Item name: %s", item.getName())).append(System.lineSeparator())
                        .append(String.format("--Item price: %.2f", item.getPrice())).append(System.lineSeparator());

                sb.append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
