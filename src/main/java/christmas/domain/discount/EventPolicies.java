package christmas.domain.discount;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class EventPolicies implements Iterable<SpecialEventPolicy> {

    private final List<SpecialEventPolicy> policies;

    private EventPolicies(List<SpecialEventPolicy> policies) {
        this.policies = Collections.unmodifiableList(policies);
    }

    public static EventPolicies from(List<SpecialEventPolicy> policies) {
        return new EventPolicies(policies);
    }

    @Override
    public Iterator<SpecialEventPolicy> iterator() {
        return policies.iterator();
    }
}
