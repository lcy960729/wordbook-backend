package com.example.wordbook.domain.word.service;

import com.example.wordbook.domain.word.dto.WordRequestDTO;
import com.example.wordbook.domain.word.entity.Word;
import com.example.wordbook.domain.word.repository.WordRepository;
import com.example.wordbook.domain.wordbook.entity.WordBook;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class CreateWordService {

    private final WordRepository wordRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public CreateWordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Long create(WordRequestDTO.Create createWordDTO) throws ChangeSetPersister.NotFoundException {
//        WordBook wordBook = wordBookRepository.findById(createWordDTO.getWordBookId()).orElseThrow(ChangeSetPersister.NotFoundException::new);
//
//        Word word = modelMapper.map(createWordDTO, Word.class);
//        word.setUsing(true);
//        word.setWordBook(wordBook);
//        word = wordRepository.save(word);
//
//        wordBook.getWords().add(word);
//        wordBookRepository.save(wordBook);

        return 0L;
    }

}
