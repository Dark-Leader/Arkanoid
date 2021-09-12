// ID: 318528189.
package interfaces;

/**
 * <p>
 *     The HitNotifier application is an interfaces indicating that objects that implement it
 *     send notifications when they are being hit.
 * </p>
 */
public interface HitNotifier {

    /**
     * Adding a hit listener.
     * @param hl HitListener.
     */
    void addHitListener(HitListener hl);
    /**
     * Removing a hit listener.
     * @param hl HitListener.
     */
    void removeHitListener(HitListener hl);
}
