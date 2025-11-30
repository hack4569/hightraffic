package ht.board.hotarticle.service.eventhandler;

import lsh.board.common.event.Event;
import lsh.board.common.event.EventPayload;

public interface EventHandler<T extends EventPayload> {
    void handle(Event<T> event);
    boolean supports(Event<T> event);
    Long findArticleId(Event<T> event);
}
