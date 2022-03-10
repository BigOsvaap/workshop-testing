package osvaap.api.core;

import org.springframework.web.bind.annotation.*;

public interface CivilService {

    /**
     * Sample usage, see below.
     *
     * curl -X POST $HOST:$PORT/civil \
     *   -H "Content-Type: application/json" --data \
     *   '{"name":"Oswaldo","lastName":"Vazquez","RFC":"ASDSCCCD221"}'
     *
     * @param body A JSON representation of the new civil
     * @return A JSON representation of the newly created civil
     */
    @PostMapping(
            value    = "/civil",
            consumes = "application/json",
            produces = "application/json")
    Civil registerCivil(@RequestBody Civil body);

    /**
     * Sample usage: "curl $HOST:$PORT/civil/ASDSCCCD221".
     *
     * @param RFC of the civil
     * @return the product, if found, else null
     */
    @GetMapping(
            value = "/civil/{RFC}",
            produces = "application/json")
    Civil getCivil(@PathVariable String RFC);

    /**
     * Sample usage: "curl -X DELETE $HOST:$PORT/civil/ASDSCCCD221".
     *
     * @param RFC of the civil
     */
    @DeleteMapping(value = "/civil/{RFC}")
    void deleteCivil(@PathVariable String RFC);

}
