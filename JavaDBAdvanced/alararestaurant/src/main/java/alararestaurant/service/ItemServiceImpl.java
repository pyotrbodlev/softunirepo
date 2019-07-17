package alararestaurant.service;

import alararestaurant.common.Texts;
import alararestaurant.domain.dtos.CategoryImportDto;
import alararestaurant.domain.dtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean itemsAreImported() {
        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() {
        return this.fileUtil.readFile(getClass().getClassLoader().getResource("files/items.json").getFile());
    }

    @Override
    public String importItems(String items) {
        ItemImportDto[] importDtos = this.gson.fromJson(items, ItemImportDto[].class);
        StringBuilder sb = new StringBuilder();

        for (ItemImportDto itemDto : importDtos) {
            CategoryImportDto categoryImportDto = new CategoryImportDto();
            categoryImportDto.setName(itemDto.getCategory());


            if (this.validationUtil.isValid(itemDto) && this.validationUtil.isValid(categoryImportDto)) {
                Item item = this.modelMapper.map(itemDto, Item.class);

                if (this.itemRepository.findByName(item.getName()).orElse(null) == null) {
                    Category category = this.categoryRepository.findByName(categoryImportDto.getName()).orElse(null);

                    if (category == null) {
                        category = this.modelMapper.map(categoryImportDto, Category.class);

                        this.categoryRepository.saveAndFlush(category);
                    }

                    item.setCategory(category);

                    this.itemRepository.saveAndFlush(item);

                    sb.append(String.format(Texts.RECORD_IMPORTED, item.getName())).append(System.lineSeparator());
                } else {
                    sb.append(String.format(Texts.DUPLICATED_DATA, item.getName())).append(System.lineSeparator());
                }

            } else {
                sb.append(Texts.INVALID_DATA).append(System.lineSeparator());
            }
        }

        return sb.toString().trim();
    }
}
