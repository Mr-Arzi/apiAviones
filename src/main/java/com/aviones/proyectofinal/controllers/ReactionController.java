package com.aviones.proyectofinal.controllers;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviones.proyectofinal.models.Avion;
import com.aviones.proyectofinal.models.AvionReaction;
import com.aviones.proyectofinal.models.Reaction;
import com.aviones.proyectofinal.models.User;
import com.aviones.proyectofinal.payload.request.AvionReactionRequest;
import com.aviones.proyectofinal.payload.response.ReactionCountResponse;
import com.aviones.proyectofinal.repository.AvionReactionRepository;
import com.aviones.proyectofinal.repository.AvionRepository;
import com.aviones.proyectofinal.repository.ReactionRepository;
import com.aviones.proyectofinal.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reactions")
public class ReactionController {

    @Autowired
    private AvionReactionRepository avionReactionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AvionRepository avionRepository;
    @Autowired
    private ReactionRepository reactionRepository;

    @GetMapping("/all")
    public Page<AvionReaction> getReaction(Pageable pageable) {
        return avionReactionRepository.findAll(pageable);
    }

    @GetMapping("/most-voted/{avionId}")
    public ResponseEntity<String> getMostVotedReaction(@PathVariable Long avionId) {
        List<Object[]> results = avionReactionRepository.findMostVotedReactionByAvionId(avionId);

        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String mostVotedReaction = results.get(0)[0].toString(); // EReaction name
        return ResponseEntity.ok(mostVotedReaction);
    }

    @PostMapping("/create")
    public AvionReaction createReaction(@Valid @RequestBody AvionReactionRequest avionReaction) {
        System.out.println("avionid : " + avionReaction.getAvionId());
        System.out.println("reactiontid : " + avionReaction.getReactionId());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        System.out.println("userid : " + userId);

        User user = getValidUser(userId);
        System.out.println("user");

        System.out.println(user);

        AvionReaction myAvionReaction = new AvionReaction();
        Avion myAvion = getValidAvion(avionReaction.getAvionId());
        System.out.println("object avion : ");
        System.out.println(myAvion);

        Reaction myReaction = getValidReaction(avionReaction.getReactionId());
        System.out.println("object reaction : ");
        System.out.println(myReaction);

        // myTweetReaction.setUserId(user.getId());
        // myTweetReaction.setTweetId(myTweet.getId());
        // myTweetReaction.setReactionId(myReaction.getId());

        myAvionReaction.setUser(user);
        myAvionReaction.setAvion(myAvion);
        myAvionReaction.setReaction(myReaction);

        System.out.println("avion reaction : ");
        System.out.println(myAvionReaction.getReactionId());
        System.out.println(myAvionReaction.getAvion_id());

        System.out.println(myAvionReaction.getUserId());

        avionReactionRepository.save(myAvionReaction);

        return myAvionReaction;
    }

    private User getValidUser(String userId) {
        Optional<User> userOpt = userRepository.findByUsername(userId);
        if (!userOpt.isPresent()) {
            throw new RuntimeException("User not found");
        }
        return userOpt.get();
    }

    private Avion getValidAvion(Long avionId) {
        Optional<Avion> avionOpt = avionRepository.findById(avionId);
        if (!avionOpt.isPresent()) {
            throw new RuntimeException("Tweet not found");
        }
        return avionOpt.get();
    }

    private Reaction getValidReaction(Long reactionId) {
        Optional<Reaction> reactionOpt = reactionRepository.findById(reactionId);
        if (!reactionOpt.isPresent()) {
            throw new RuntimeException("Reaction not found");
        }
        return reactionOpt.get();
    }

    @GetMapping("/count/{avionId}")
public ResponseEntity<List<ReactionCountResponse>> countReactions(@PathVariable Long avionId) {
    List<Object[]> results = avionReactionRepository.findMostVotedReactionByAvionId(avionId);
    
    List<ReactionCountResponse> response = results.stream()
        .map(r -> new ReactionCountResponse((String) r[0], (Long) r[1]))
        .toList();

    return ResponseEntity.ok(response);
}


}
