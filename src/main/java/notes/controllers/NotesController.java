package notes.controllers;


import notes.dao.WebNoteDao;
import notes.models.WebNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NotesController {

    private final WebNoteDao webNoteDao;

    @Autowired
    public NotesController(WebNoteDao webNoteDao){
        this.webNoteDao = webNoteDao;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("notes", webNoteDao.index());
        return "notes/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("note", webNoteDao.show(id));
        return "notes/show";
    }

    @GetMapping("/new")
    public String newNote(Model model){
        model.addAttribute("webNote", new WebNote());
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("webNote") WebNote webNote){
        webNoteDao.save(webNote);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){
        model.addAttribute("webNote", webNoteDao.show(id));
        return "notes/id";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("webNote") WebNote webNote, @PathVariable("id") int id){
        webNoteDao.update(id, webNote);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        webNoteDao.delete(id);
        return "redirect:/notes";
    }
}
