package main.service;

import java.util.List;
import main.entity.Journal;

public interface JournalService {

  List<Journal> journalList();

  Journal findJournal(Long id);

  Journal addJournal(Journal journal);

  void deleteJournal(Long id);
}
