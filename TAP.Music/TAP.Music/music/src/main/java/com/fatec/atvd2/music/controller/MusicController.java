package com.fatec.atvd2.music.controller;

import com.fatec.atvd2.music.model.Music;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/musics")
public class MusicController {

    private List<Music> musics = new ArrayList<>();
    private Long nextId = 4L;

    public MusicController() {
        musics.add(new Music(1L, "Shape of You",     "Ed Sheeran", "Divide",           240, "Pop"));
        musics.add(new Music(2L, "Blinding Lights",  "The Weeknd", "After Hours",       200, "Synth-pop"));
        musics.add(new Music(3L, "Levitating",       "Dua Lipa",   "Future Nostalgia",  203, "Disco-pop"));
    }

    // GET /musics — lista todas
    @GetMapping
    public List<Music> getAll() {
        return musics;
    }

    // GET /musics/{id} — busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Music> getById(@PathVariable Long id) {
        Music music = findById(id);
        if (music == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(music);
    }

    // POST /musics — cria nova música
    @PostMapping
    public Music create(@RequestBody Music music) {
        music.setId(nextId++);
        musics.add(music);
        return music;
    }

    // PUT /musics/{id} — atualiza música existente
    @PutMapping("/{id}")
    public ResponseEntity<Music> update(@PathVariable Long id, @RequestBody Music updated) {
        Music music = findById(id);
        if (music == null) return ResponseEntity.notFound().build();

        music.setTitle(updated.getTitle());
        music.setArtist(updated.getArtist());
        music.setAlbum(updated.getAlbum());
        music.setDuration(updated.getDuration());
        music.setGenre(updated.getGenre());

        return ResponseEntity.ok(music);
    }

    // DELETE /musics/{id} — remove música
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Music music = findById(id);
        if (music == null) return ResponseEntity.notFound().build();

        musics.remove(music);
        return ResponseEntity.noContent().build();
    }

    // Método auxiliar
    private Music findById(Long id) {
        for (Music m : musics) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }
}
