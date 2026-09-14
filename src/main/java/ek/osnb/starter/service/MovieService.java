package ek.osnb.starter.service;

import ek.osnb.starter.exceptions.NotFoundException;
import ek.osnb.starter.model.Actor;
import ek.osnb.starter.model.Movie;
import ek.osnb.starter.repository.ActorRepository;
import ek.osnb.starter.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    public MovieService(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("Movie not found with id: " + id);
        }
        // Or use shortcut:
        // return movieRepository.findById(id).orElseThrow(() -> new NotFoundException("Movie not found with id: " + id));

        return movieOptional.get();
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    public Movie addActorToMovie(Long movieId, Long actorId) {
        // TODO: Find the movie by ID (throw exception if not found)
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isEmpty()) {
            throw new NotFoundException("Movie not found with id: " + movieId);
        }
        Movie movie = movieOptional.get();

        // TODO: Find the actor by ID (throw exception if not found)
        Optional<Actor> actorOptional = actorRepository.findById(actorId);
        if (actorOptional.isEmpty()) {
            throw new NotFoundException("Actor not found with id: " + actorId);
        }
        Actor actor = actorOptional.get();

        // TODO: Add actor to movie's actors list
        movie.getActors().add(actor);

        // TODO: Save and return the updated movie
        return movieRepository.save(movie);
    }
}